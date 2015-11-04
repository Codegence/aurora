#include <base/Controller.h>
#include <jsoncpp/json/writer.h>
#include <model/Constants.h>

/** Send HTTP status. */
bool Controller::sendStatus(Poco::Net::HTTPServerResponse& res, Poco::Net::HTTPResponse::HTTPStatus status) {
    res.setStatusAndReason(status);
    res.setContentLength(0);
    res.send();
    return true;
}

/** Send JSON response. */
bool Controller::sendJson(Poco::Net::HTTPServerResponse& res, const Json::Value& json) {
    std::string resStr = Json::FastWriter().write(json);
    res.setContentType(JSON_MIME);
    res.sendBuffer(resStr.c_str(), resStr.size());
    return true;
}
