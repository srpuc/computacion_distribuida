//PACKAGE
package jade_agentes;


//IMPORTS
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import java.util.ArrayList;


//CLASS SUBASTA
//-------------
public class agente_subasta extends Agent {
    
    //LOCAL VARIABLES
    private String bookName;
    private float  bookPrice;
    private float  bookIncrementPrice;
    
    private AID   bookWinningOfferAgent;
    private float bookHighestBid;
    
    private AID[]          agents;
    private ArrayList<AID> activeAgents;
    private ArrayList<AID> allAgents;
    
    private MessageTemplate mt;
    
    
    //SETUP METHOD
    protected void setup() {

        //arguments
        String input = (String) getArguments()[0];
        String[] args  = input.split(" ");
        
        if(args != null && args.length == 3) {
            
            //initialize local variables
            bookName           = (String) args[0];
            bookPrice          = Float.parseFloat((String) args[1]);
            bookIncrementPrice = Float.parseFloat((String) args[2]);
            
            bookWinningOfferAgent = null;
            bookHighestBid        = 0;
            
            activeAgents = new ArrayList();
            allAgents    = new ArrayList();
            
            
            //print info
            System.out.println("Hello! agente_subasta " + getAID().getName() + " is ready." );
            System.out.println("Auctioning \"" + bookName + "\", starting price: " + bookPrice + ".");
            
            //add recieverOffer behaviour
            addBehaviour( new recieveOffer() );
            
            //update bid every 10 seconds
            addBehaviour( new TickerBehaviour(this, 10000) {
                
                //executes periodically
                protected void onTick() {
                    
                    //LAST BID UPDATE
                    //---------------
                    System.out.println("----------INFO---------");
                    System.out.println("Book:" + bookName);
                    System.out.println("Price: " + bookPrice);
                    System.out.println("Agents size: " + allAgents.size());
                    
                    //still remain bidders
                    if ( activeAgents.size() > 0 )
                        for( AID a : activeAgents ) {

                            //loser bids
                            if ( !a.getName().equals( bookWinningOfferAgent.getName() ) ) {

                                //prepare message
                                ACLMessage msg = new ACLMessage( ACLMessage.REJECT_PROPOSAL );
                                msg.addReceiver(a);
                                msg.setContent("Bid not high enough.");
                                msg.setConversationId("trade");
                                msg.setReplyWith("order" + System.currentTimeMillis());

                                //send message
                                myAgent.send( msg );
   
                            }
                            // winner bid
                            else {

                                //prepare message
                                ACLMessage msg = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
                                msg.addReceiver(a);
                                msg.setContent("Highest bidder.");
                                msg.setConversationId("trade");
                                msg.setReplyWith("order" + System.currentTimeMillis());

                                //send message
                                myAgent.send(msg);
                                
                                //
                                //mt = MessageTemplate.and(MessageTemplate.MatchConversationId("book-trade"),MessageTemplate.MatchInReplyTo(msg.getReplyWith()));

                            }

                        }
                    
                    //no bidders remain
                    if ( activeAgents.size() <= 1 )
                        for ( AID a : allAgents )
                            if ( !a.getName().equals( bookWinningOfferAgent.getName() ) ){
                                
                                //prepare message
                                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                                msg.addReceiver(a);
                                msg.setContent("Aunction ended. You have lost.");
                                msg.setConversationId("trade");
                                msg.setReplyWith("order" + System.currentTimeMillis());
                                
                                //send message
                                myAgent.send(msg);
                                
                            }
                            else {
                                
                                //update price
                                bookPrice -= bookIncrementPrice;
                                
                                //prepare message
                                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                                msg.addReceiver(a);
                                msg.setContent("Aunction ended. You have won. Final price: " + bookPrice + ".");
                                msg.setConversationId("trade");
                                msg.setReplyWith("order" + System.currentTimeMillis());
                                
                                //send message
                                myAgent.send(msg);
                                
                                //
                                //mt = MessageTemplate.and(MessageTemplate.MatchConversationId("book-trade"),MessageTemplate.MatchInReplyTo(msg.getReplyWith()));
                                
                                //end auction agent
                                myAgent.doDelete();
                                
                            }
                            
                    
                    //NEW BID ROUND
                    //-------------
                    
                    //reset
                    activeAgents.clear();
                    
                    //print info
                    System.out.println("BID ROUND.");
                    
                    //df
                    DFAgentDescription dfd = new DFAgentDescription();
                    
                    //service
                    ServiceDescription sd       = new ServiceDescription();
                    sd.setType( "libros" );
                    
                    //add service
                    dfd.addServices(sd);
                    try{
                        
                        //search for bidders
                        DFAgentDescription[] result = DFService.search(myAgent, dfd);
                        agents = new AID[result.length];
                        
                        //initialize agent array
                        for ( int i = 0; i < result.length; i++ ) {
                            agents[i] = result[i].getName();
                        }
                        
                        //create cfp message (call for proposal)
                        ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
                        for( int i = 0; i < result.length; i++ )
                            cfp.addReceiver(agents[i]);
                        cfp.setContent( String.valueOf( bookPrice ) );
                        cfp.setConversationId( "trade" );
                        cfp.setReplyWith("cfp" + System.currentTimeMillis());
                        
                        //send cfp
                        myAgent.send(cfp);
                        
                        //
                        //mt = MessageTemplate.and(MessageTemplate.MatchConversationId("book-trade"), MessageTemplate.MatchInReplyTo(cfp.getReplyWith()));
                        
                        //update price
                        bookPrice += bookIncrementPrice;
                        
                    } 
                    catch (FIPAException ex) {
                        ex.printStackTrace();
                    }
                                      
                } //end onTick
                
            } ); //end addBehaviour

        }
        else {
            System.out.println("Invalid arguments.");
            doDelete();
        }
        
    } //end of setup
    
    
    //TAKEDOWN METHOD
    protected void takeDown() {
        
        //print a exit message
        System.out.println("Ops! agente_subasta " + getAID().getName() + " terminating.");
        
    }
    
    
    //CLASS BEHAVIOUR
    //---------------
    private class recieveOffer extends CyclicBehaviour {

        //ACTION METHOD
        //this is executed until agent is terminated
        public void action() {
            
            //receive reply
            //mt = MessageTemplate.MatchPerformative(ACLMessage.PROPOSE);
            ACLMessage reply = myAgent.receive(mt);
            
            //check reply
            if ( reply != null ) {
                
                //check type of message
                if( reply.getPerformative() == ACLMessage.PROPOSE ) {
                    
                    //get offer price
                    float offerPrice = Float.parseFloat( reply.getContent() );
                    
                    //check offer
                    if ( bookWinningOfferAgent == null || offerPrice > bookHighestBid ) {
                        bookWinningOfferAgent = reply.getSender();
                        bookHighestBid = offerPrice;
                    }
                    
                    //add active agent
                    activeAgents.add( reply.getSender() );
                    
                    //add agent to all agetns list
                    if ( !allAgents.contains( reply.getSender() ) )
                        allAgents.add( reply.getSender() );
                    
                }
                
            }
            
        }
        
    }
    
    
}
