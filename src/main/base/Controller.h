#ifndef CONTROLLER_H
#define CONTROLLER_H
#include <base/Mapping.h>
#include <forward_list>
#include <jsoncpp/json/value.h>

/** @brief Controller ancestor. */
class Controller
{
protected:
    /**
     * @brief Send an status response.
     * @param res Response to write into.
     * @param status Status to write.
     * @return Always true since it consumes the request.
     */
    bool sendStatus(Poco::Net::HTTPServerResponse& res, Poco::Net::HTTPResponse::HTTPStatus status);

    /**
     * @brief Send a json response.
     * @param res Response to write into.
     * @param json Json to respond.
     * @return Always true since it consumes the request.
     */
    bool sendJson(Poco::Net::HTTPServerResponse& res, const Json::Value& json);

public:
    /** @brief Destructor. */
    virtual ~Controller() {}

    /** @brief Register URL mappings. */
    virtual std::forward_list<Mapping> listMappings() = 0;
};

#endif // CONTROLLER_H
