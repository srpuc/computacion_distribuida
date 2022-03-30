//PACKAGE
package jade_agentes;


//IMPORTS
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;


//CLASS PUJA
//----------
public class agente_puja extends Agent {

    //LOCAL VARIABLES
    private String bookName;
    private float  bookMaximumPrice;
    
    
    //SETUP METHOD
    protected void setup() {
      
        //arguments
        String input = (String) getArguments()[0];
        String[] args  = input.split(" ");
        
        if(args != null && args.length == 2) {
            
            //initialize local variables
            bookName = (String) args[0];
            bookMaximumPrice = Float.parseFloat((String) args[1]);
            
            //print info
            System.out.println("Hello! agente_puja " + getAID().getName() + " is ready." );
            System.out.println("Searching for \"" + bookName + "\" at a maximum price of " + bookMaximumPrice + ".");
            
            //df
            DFAgentDescription dfd = new DFAgentDescription();
            dfd.setName(getAID());
            
            //sercive
            ServiceDescription sd = new ServiceDescription();
            sd.setName("jade_libros");
            sd.setType("libros");
            
            //add service
            dfd.addServices(sd);
            try{
                DFService.register(this, dfd);
            } 
            catch (FIPAException ex) {
                ex.printStackTrace();
            }
            
            //add requestOffer behaviour
            addBehaviour( new requestOffer() );
            
        }
        else {
            System.out.println("Incorrect arguments.");
            doDelete();
        }
        

        
    }
    
    //TAKEDOWN METHOD
    protected void takeDown() {
        
        //unregister agent
        try{
            DFService.deregister(this);
        } 
        catch (FIPAException ex) {
            ex.printStackTrace();
        }
        
        //print a exit message
        System.out.println("Ops! agente_puja " + getAID().getName() + " terminating.");
        
    }
    
    
    //CLASS BEHAVIOUR
    //---------------
    private class requestOffer extends CyclicBehaviour {

        //ACTION METHOD
        //this is executed until agent is terminated
        public void action() {
            
            //recieve msg
            MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.CFP);
            ACLMessage     msg = myAgent.receive(mt);
            
            //check message
            if (msg != null) {
                
                //process cfp message
                float      price = Float.parseFloat( msg.getContent() );
                ACLMessage reply = msg.createReply();
                
                //check price
                if ( price <= bookMaximumPrice ) {
                    //offer
                    reply.setPerformative( ACLMessage.PROPOSE );
                    reply.setContent( String.valueOf( bookMaximumPrice ) );
                }
                else {
                    //deny
                    reply.setPerformative(ACLMessage.REFUSE);
                    reply.setContent("Maximum price reached.");
                }
                
                //send message
                myAgent.send(reply);
                
            }
            else {
                block();
            }
            
        }
        
    }
    
}
