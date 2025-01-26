package theVaultHunter0.Server;

import theVaultHunter0.Header.Header;

/**
 * Class with a request and response header default to compare with what we receive and send.
 * We specify what each Header should look like. Making sure that communication is made smoothly.
 * If the different header were not use, no default is used.
 */
public class DefaultHeaders {
    private static Header requestHeader = new Header();
    private static boolean emptyRequestDefault = true; //Use to create what the requests header should minimally have.
    private static Header responseHeader = new Header();
    private static boolean emptyResponseDefault = true;//Use to create what the response header will have.

    public static Header getRequestHeader() {
        return requestHeader;
    }

    public static Header getResponseHeader() {
        return responseHeader;
    }

    /**
     * Verify if the RequestDefaultHeader is used.
     * @return true if a section of the RequestDefaultHeader was added. False if not section was added.
     */
    public static boolean isRequestDefaultEmpty(){
        return emptyRequestDefault;
    }

    /**
     * Verify if the ResponseDefaultHeader is used.
     * @return true if a section of the ResponseDefaultHeader was added. False if not section was added.
     */
    public static boolean isResponseDefaultEmpty(){
        return emptyResponseDefault;
    }

    private static boolean validateSection(String section){
        String nSection = section.toLowerCase();
        switch (nSection) {
            case "response", "general", "entity", "security", "custom", "request" -> {
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    /**
     * Add a section to the request default header
     * @param section Request, General, Entity, Security, Custom, Request. (Case sensitive)
     * @throws Error if the String section isn't recognise
     */
    public static void addSectionToRequestDefault(String section){
        String nSection = section.toLowerCase();
        if(validateSection(nSection)){
            switch (nSection) {
                case "request" -> {requestHeader.addRequestSpecificHeader();
                    emptyRequestDefault = false;}
                case "general" -> {requestHeader.addGeneralHeader();
                    emptyRequestDefault = false;}
                case "entity" -> {requestHeader.addEntityHeader();
                    emptyRequestDefault = false;}
                case "security" -> {requestHeader.addSecurityHeader();
                    emptyRequestDefault = false;}
                case "custom" -> {requestHeader.addCustomHeader();
                    emptyRequestDefault = false;}
                case "response" -> throw new Error("Cant add a response header in a request header.");
                default -> throw new Error("Section : " + section + " not recognized when adding section to default request header.");
            }
        }
        throw new Error("Section : " + section + " not recognized when adding section to default request header.");
    }

    /**
     * Add a section to the response default header
     * @param section Response, General, Entity, Security, Custom, Request. (Case sensitive)
     * @throws Error if the String section isn't recognise
     */
    public static void addSectionToResponseDefault(String section){
        String nSection = section.toLowerCase();
        if(!validateSection(nSection)){
            throw new Error("Section : " + section + " not recognized when adding section to default response header.");
        }
        switch (nSection) {
            case "response" -> {responseHeader.addResponseSpecificHeader();
                emptyResponseDefault = false;}
            case "general" -> {responseHeader.addGeneralHeader();
                emptyResponseDefault = false;}
            case "entity" -> {responseHeader.addEntityHeader();
                emptyResponseDefault = false;}
            case "security" -> {responseHeader.addSecurityHeader();
                emptyResponseDefault = false;}
            case "custom" -> {responseHeader.addCustomHeader();
                emptyResponseDefault = false;}
            case "request" -> throw new Error("Cant add a request header in a response header.");
            default -> {
                throw new Error("Section : " + section + " not recognized when adding section to default response header.");
            }
        }
    }

    /**
     * Add a value to a parameter to a section to the response default header
     * @param section response, general, entity, security, custom, request
     * @param parameter string of a parameter of one of the header section
     * @param value string of the value of the parameter
     * @throws Error if the String section or parameter isn't recognise
     */
    public static void addParameterToResponseDefault(String section, String parameter, String value) throws IllegalAccessException {
        String nSection = section.toLowerCase();
        //Check if the section exist
        if (validateSection(nSection)) {
            switch (nSection) {
                case "response" -> {
                    //Check if section was used as default
                    if(responseHeader.validateSection(nSection)){
                        //Check if parameter exist
                        if(responseHeader.getResponseHeader().verifyParameterExist(parameter)){
                            //Then assign the value to the parameter
                            responseHeader.getResponseHeader().assignValueToParameter(parameter, value);
                            return;
                        }
                        throw new Error("Parameter: " + parameter + " not recognize when adding parameter to default response header.");
                    }
                    throw new Error("Section : " + section + " is not initialised when adding parameter : " + parameter + " to response default.");
                }
                case "general" -> {
                    //Check if section was used as default
                    if(responseHeader.validateSection(nSection)){
                        //Check if parameter exist
                        if(responseHeader.getGeneralHeader().verifyParameterExist(parameter)){
                            //Then assign the value to the parameter
                            responseHeader.getGeneralHeader().assignValueToParameter(parameter, value);
                            return;
                        }
                        throw new Error("Parameter: " + parameter + " not recognize when adding parameter : " + parameter + " to default response header.");
                    }
                    throw new Error("Section : " + section + " is not initialised when adding parameter : " + parameter + " to response default.");
                }
                case "entity" -> {
                    //Check if section was used as default
                    if(responseHeader.validateSection(nSection)){
                        //Check if parameter exist
                        if(responseHeader.getEntityHeader().verifyParameterExist(parameter)){
                            //Then assign the value to the parameter
                            responseHeader.getEntityHeader().assignValueToParameter(parameter, value);
                            return;
                        }
                        throw new Error("Parameter: " + parameter + " not recognize when adding parameter : " + parameter + " to default response header.");
                    }
                    throw new Error("Section : " + section + " is not initialised when adding parameter : " + parameter + " to response default.");
                }
                case "security" -> {
                    //Check if section was used as default
                    if(responseHeader.validateSection(nSection)){
                        //Check if parameter exist
                        if(responseHeader.getSecurityHeader().verifyParameterExist(parameter)){
                            //Then assign the value to the parameter
                            responseHeader.getSecurityHeader().assignValueToParameter(parameter, value);
                            return;
                        }
                        throw new Error("Parameter: " + parameter + " not recognize when adding parameter : " + parameter + " to default response header.");
                    }
                    throw new Error("Section : " + section + " is not initialised when adding parameter : " + parameter + " to response default.");
                }
                case "custom" -> {
                    //Check if section was used as default
                    if(responseHeader.validateSection(nSection)){
                        //Check if parameter exist
                        if(responseHeader.getCustomHeader().verifyParameterExist(parameter)){
                            //Then assign the value to the parameter
                            responseHeader.getCustomHeader().assignValueToParameter(parameter, value);
                            return;
                        }
                        throw new Error("Parameter: " + parameter + " not recognize when adding parameter : " + parameter + " to default response header.");
                    }
                    throw new Error("Section : " + section + " is not initialised when adding parameter : " + parameter + " to response default.");
                }
                case "request" -> {
                    //Check if section was used as default
                    if(responseHeader.validateSection(nSection)){
                        //Check if parameter exist
                        if(responseHeader.getRequestHeader().verifyParameterExist(parameter)){
                            //Then assign the value to the parameter
                            responseHeader.getRequestHeader().assignValueToParameter(parameter, value);
                            return;
                        }
                        throw new Error("Parameter: " + parameter + " not recognize when adding parameter : " + parameter + " to default response header.");
                    }
                    throw new Error("Section : " + section + " is not initialised when adding parameter : " + parameter + " to response default.");
                }
                default -> throw new Error("Section not recognized when adding section to default response header.");
            }
        }
        throw new Error("Section not recognized when adding parameter to default response header.");
    }

    /**
     * Add a value to a parameter to a section to the request default header
     * @param section response, general, entity, security, custom, request
     * @param parameter string of a parameter of one of the header section
     * @param value string of the value of the parameter
     * @throws Error if the String section or parameter isn't recognise
     */
    public static void addParameterToRequestDefault (String section, String parameter, String value) throws IllegalAccessException {
        String nSection = section.toLowerCase();
        //Check if the section exist
        if (validateSection(nSection)) {
            switch (nSection) {
                case "response" -> {
                    //Check if section was used as default
                    if(requestHeader.validateSection(nSection)){
                        //Check if parameter exist
                        if(requestHeader.getResponseHeader().verifyParameterExist(parameter)){
                            //Then assign the value to the parameter
                            requestHeader.getResponseHeader().assignValueToParameter(parameter, value);
                            return;
                        }
                        throw new Error("Parameter: " + parameter + "not recognizer when adding parameter to default response header.");
                    }
                    throw new Error("Section : " + section + " is not initialised when adding parameter : " + parameter + " to response default.");
                }
                case "general" -> {
                    //Check if section was used as default
                    if(requestHeader.validateSection(nSection)){
                        //Check if parameter exist
                        if(requestHeader.getGeneralHeader().verifyParameterExist(parameter)){
                            //Then assign the value to the parameter
                            requestHeader.getGeneralHeader().assignValueToParameter(parameter, value);
                            return;
                        }
                        throw new Error("Parameter: " + parameter + "not recognizer when adding parameter : " + parameter + "to default response header.");
                    }
                    throw new Error("Section : " + section + " is not initialised when adding parameter : " + parameter + " to response default.");
                }
                case "entity" -> {
                    //Check if section was used as default
                    if(requestHeader.validateSection(nSection)){
                        //Check if parameter exist
                        if(requestHeader.getEntityHeader().verifyParameterExist(parameter)){
                            //Then assign the value to the parameter
                            requestHeader.getEntityHeader().assignValueToParameter(parameter, value);
                            return;
                        }
                        throw new Error("Parameter: " + parameter + "not recognizer when adding parameter : " + parameter + "to default response header.");
                    }
                    throw new Error("Section : " + section + " is not initialised when adding parameter : " + parameter + " to response default.");
                }
                case "security" -> {
                    //Check if section was used as default
                    if(requestHeader.validateSection(nSection)){
                        //Check if parameter exist
                        if(requestHeader.getSecurityHeader().verifyParameterExist(parameter)){
                            //Then assign the value to the parameter
                            requestHeader.getSecurityHeader().assignValueToParameter(parameter, value);
                            return;
                        }
                        throw new Error("Parameter: " + parameter + "not recognizer when adding parameter : " + parameter + "to default response header.");
                    }
                    throw new Error("Section : " + section + " is not initialised when adding parameter : " + parameter + " to response default.");
                }
                case "custom" -> {
                    //Check if section was used as default
                    if(requestHeader.validateSection(nSection)){
                        //Check if parameter exist
                        if(requestHeader.getCustomHeader().verifyParameterExist(parameter)){
                            //Then assign the value to the parameter
                            requestHeader.getCustomHeader().assignValueToParameter(parameter, value);
                            return;
                        }
                        throw new Error("Parameter: " + parameter + "not recognizer when adding parameter : " + parameter + "to default response header.");
                    }
                    throw new Error("Section : " + section + " is not initialised when adding parameter : " + parameter + " to response default.");
                }
                case "request" -> {
                    //Check if section was used as default
                    if(requestHeader.validateSection(nSection)){
                        //Check if parameter exist
                        if(requestHeader.getRequestHeader().verifyParameterExist(parameter)){
                            //Then assign the value to the parameter
                            requestHeader.getRequestHeader().assignValueToParameter(parameter, value);
                            return;
                        }
                        throw new Error("Parameter: " + parameter + "not recognizer when adding parameter : " + parameter + "to default response header.");
                    }
                    throw new Error("Section : " + section + " is not initialised when adding parameter : " + parameter + " to response default.");
                }
                default -> throw new Error("Section not recognized when adding section to default response header.");
            }
        }
        throw new Error("Section not recognized when adding parameter to default response header.");
    }
}