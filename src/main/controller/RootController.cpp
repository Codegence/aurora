#include <controller/RootController.h>
#include <jsoncpp/json/reader.h>
#include <jsoncpp/json/writer.h>
#include <Poco/Util/ServerApplication.h>
#include <functional>
#include <base/Globals.h>

/** List controller mappings. */
std::forward_list<Mapping> RootController::listMappings() {
    std::forward_list<Mapping> mappings;
    MAPPING_OPTIONS(mappings, "/.*", RootController::access);
    MAPPING_GET(mappings, "/", RootController::welcome);
    MAPPING_DELETE(mappings, "/", RootController::stop);
    return mappings;
}

/** Provide the access options. */
bool RootController::access(Poco::Net::HTTPServerRequest& req, Poco::Net::HTTPServerResponse& res,
                            boost::xpressive::smatch& sm) {
    // allow cors
    res.set("Access-Control-Allow-Headers", "origin, content-type, accept");
    res.set("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE");
    sendStatus(res, Poco::Net::HTTPResponse::HTTP_OK);
    return true;
}

/** Provide the greeter message. */
bool RootController::welcome(Poco::Net::HTTPServerRequest& req, Poco::Net::HTTPServerResponse& res,
                             boost::xpressive::smatch& sm) {
    // greeter
    res.send() << configs->getString("greeter");
    return true;
}

/** Show stopper */
bool RootController::stop(Poco::Net::HTTPServerRequest& req, Poco::Net::HTTPServerResponse& res,
                          boost::xpressive::smatch& sm) {
    // stop the server
    Poco::Util::ServerApplication::terminate();
    return sendStatus(res, Poco::Net::HTTPResponse::HTTP_OK);
}
