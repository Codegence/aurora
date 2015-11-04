#include <base/RequestHandler.h>

/** Constructor. */
RequestHandler::RequestHandler(RequestMapper *mapper) : mapper(mapper) {
}

/** Handler request. */
void RequestHandler::handleRequest(Poco::Net::HTTPServerRequest &request, Poco::Net::HTTPServerResponse &response) {
    // pass though to the mapper
    mapper->handleRequest(request, response);
}
