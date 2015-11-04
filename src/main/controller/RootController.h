#ifndef ROOTCONTROLLER_H
#define ROOTCONTROLLER_H
#include <base/Controller.h>

/** @brief Controller for the API relative to the root. */
class RootController : public Controller
{
public:
    /** @brief Constructor. */
    RootController() {}

    /** @brief Destructor. */
    virtual ~RootController() {}

    /**
     * @brief List controller mappings.
     * @return Vector of mappings.
     */
    virtual std::forward_list<Mapping> listMappings();

    /**
     * @brief Access granter.
     * @param req Request.
     * @param res Response.
     * @param sm Regex matches.
     * @return True if the request is consumed.
     */
    bool access(Poco::Net::HTTPServerRequest& req, Poco::Net::HTTPServerResponse& res, boost::xpressive::smatch& sm);

    /**
     * @brief World greeter.
     * @param req Request.
     * @param res Response.
     * @param sm Regex matches.
     * @return True if the request is consumed.
     */
    bool welcome(Poco::Net::HTTPServerRequest& req, Poco::Net::HTTPServerResponse& res, boost::xpressive::smatch& sm);

    /**
     * @brief World stopper.
     * @param req Request.
     * @param res Response.
     * @param sm Regex matches.
     * @return True if the request is consumed.
     */
    bool stop(Poco::Net::HTTPServerRequest& req, Poco::Net::HTTPServerResponse& res, boost::xpressive::smatch& sm);
};

#endif // ROOTCONTROLLER_H
