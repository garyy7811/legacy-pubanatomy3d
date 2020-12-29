
/**
 * EUtilsServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package gov.nih.nlm.ncbi.www.soap.eutils;

    /**
     *  EUtilsServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class EUtilsServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public EUtilsServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public EUtilsServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for run_eSearch method
            * override this method for handling normal response from run_eSearch operation
            */
           public void receiveResultrun_eSearch(
                    gov.nih.nlm.ncbi.www.soap.eutils.EUtilsServiceStub.ESearchResult result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from run_eSearch operation
           */
            public void receiveErrorrun_eSearch(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for run_eLink method
            * override this method for handling normal response from run_eLink operation
            */
           public void receiveResultrun_eLink(
                    gov.nih.nlm.ncbi.www.soap.eutils.EUtilsServiceStub.ELinkResult result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from run_eLink operation
           */
            public void receiveErrorrun_eLink(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for run_eInfo method
            * override this method for handling normal response from run_eInfo operation
            */
           public void receiveResultrun_eInfo(
                    gov.nih.nlm.ncbi.www.soap.eutils.EUtilsServiceStub.EInfoResult result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from run_eInfo operation
           */
            public void receiveErrorrun_eInfo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for run_eSpell method
            * override this method for handling normal response from run_eSpell operation
            */
           public void receiveResultrun_eSpell(
                    gov.nih.nlm.ncbi.www.soap.eutils.EUtilsServiceStub.ESpellResult result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from run_eSpell operation
           */
            public void receiveErrorrun_eSpell(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for run_eSummary method
            * override this method for handling normal response from run_eSummary operation
            */
           public void receiveResultrun_eSummary(
                    gov.nih.nlm.ncbi.www.soap.eutils.EUtilsServiceStub.ESummaryResult result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from run_eSummary operation
           */
            public void receiveErrorrun_eSummary(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for run_eGquery method
            * override this method for handling normal response from run_eGquery operation
            */
           public void receiveResultrun_eGquery(
                    gov.nih.nlm.ncbi.www.soap.eutils.EUtilsServiceStub.Result result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from run_eGquery operation
           */
            public void receiveErrorrun_eGquery(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for run_ePost method
            * override this method for handling normal response from run_ePost operation
            */
           public void receiveResultrun_ePost(
                    gov.nih.nlm.ncbi.www.soap.eutils.EUtilsServiceStub.EPostResult result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from run_ePost operation
           */
            public void receiveErrorrun_ePost(java.lang.Exception e) {
            }
                


    }
    