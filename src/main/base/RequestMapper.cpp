#include <base/RequestMapper.h>
#include <base/RequestHandler.h>

/** Register the controller and the mappings. */
RequestMapper::RequestMapper(std::initializer_list<std::shared_ptr<Controller>> controllerRegs) {
    for(auto &controller : controllerRegs) {
        // register controller
        controllers.push_front(controller);
        // register mappings
        for(auto &mapping : controller->listMappings())
            mappings.push_front(mapping);
    }
}

/** Obtain the handler for a request. */
Poco::Net::HTTPRequestHandler* RequestMapper::createRequestHandler(const Poco::Net::HTTPServerRequest& request) {
    // create an object for this particular request
    return new RequestHandler(this);
}

/** Handle request. */
void RequestMapper::handleRequest(Poco::Net::HTTPServerRequest& request, Poco::Net::HTTPServerResponse& response) {
    // add the access control header
    response.set("Access-Control-Allow-Origin", "*");
    response.set("Access-Control-Allow-Credentials", "true");

    // try to match a mapping
    boost::xpressive::smatch sm;
    for(Mapping& mapping : mappings)
        if(request.getMethod() == mapping.method && boost::xpressive::regex_match(request.getURI(), sm, mapping.rex)
                && mapping.func(request, response, sm))
            return;

    // send invalid request
    response.setStatusAndReason(Poco::Net::HTTPResponse::HTTP_BAD_REQUEST);
    response.setContentLength(0);
    response.send();
}
