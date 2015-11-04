#ifndef REQUESTHANDLER_H
#define REQUESTHANDLER_H
#include <Poco/Net/HTTPRequestHandler.h>
#include <base/RequestMapper.h>

/** @brief Handle a single request. */
class RequestHandler :  public Poco::Net::HTTPRequestHandler
{
private:
    RequestMapper *mapper; /**< Request mapper asigned. */

public:
    /**
     * @brief Constructor.
     * @param mapper Request mapper to use.
     */
    RequestHandler(RequestMapper *mapper);

    /**
     * @brief Process a request.
     * @param request Request to process.
     * @param response Response to return.
     */
    virtual void handleRequest(Poco::Net::HTTPServerRequest& request, Poco::Net::HTTPServerResponse& response);
};

#endif // REQUESTHANDLER_H
