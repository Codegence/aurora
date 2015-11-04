#ifndef REQUESTMAPPER_H
#define REQUESTMAPPER_H
#include <Poco/Net/HTTPRequestHandlerFactory.h>
#include <forward_list>
#include <base/Controller.h>

/** @brief Request mapper and dispatcher. */
class RequestMapper : public Poco::Net::HTTPRequestHandlerFactory
{
private:
    std::forward_list<Mapping> mappings; /**< @brief Mappings holder. */
    std::forward_list<std::shared_ptr<Controller>> controllers; /**< @brief Controllers holder. */

public:
    /**
     * @brief Request mapper constructor.
     * @param controllers Controllers to register in this mapper.
     */
    RequestMapper(std::initializer_list<std::shared_ptr<Controller>> controllerRegs);

    /**
     * @brief Factory method to create specific request handlers.
     * @param request Request to process.
     * @return Request handler to be used.
     */
    Poco::Net::HTTPRequestHandler* createRequestHandler(const Poco::Net::HTTPServerRequest& request);

    /**
     * @brief Process a request.
     * @param request Request to process.
     * @param response Response to return.
     */
    virtual void handleRequest(Poco::Net::HTTPServerRequest& request, Poco::Net::HTTPServerResponse& response);
};

#endif // REQUESTMAPPER_H
