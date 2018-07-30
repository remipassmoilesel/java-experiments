# Page d'erreur personnalisée

/!\ Attention, le template nommé "error.html" sera utilisé par défaut

Implémenter l'interface ErrorController et associer un template:
    
    @Controller
    public class CustomErrorController implements ErrorController {
    
        @RequestMapping(value = Mappings.ERROR, method = RequestMethod.GET)
        public String renderErrorPage(Model model, HttpServletRequest httpRequest) {
    
            System.out.println("httpRequest");
            System.out.println(httpRequest);
            System.out.println(httpRequest.getAttributeNames());
    
            String errorMsg = "";
            int httpErrorCode = getErrorCode(httpRequest);
    
            switch (httpErrorCode) {
                case 400: {
                    errorMsg = "Http Error Code: 400. Bad Request";
                    break;
                }
                case 401: {
                    errorMsg = "Http Error Code: 401. Unauthorized";
                    break;
                }
                case 404: {
                    errorMsg = "Http Error Code: 404. Resource not found";
                    break;
                }
                case 500: {
                    errorMsg = "Http Error Code: 500. Internal Server Error";
                    break;
                }
            }
    
            model.addAttribute("date", new Date());
            model.addAttribute("errorMsg", errorMsg);
            return "errorPage";
        }
    
        private int getErrorCode(HttpServletRequest httpRequest) {
            return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
        }
    
        @Override
        public String getErrorPath() {
            return Mappings.ERROR;
        }
    }
