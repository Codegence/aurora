#ifndef MAPPING_H
#define MAPPING_H
#include <boost/xpressive/xpressive.hpp>
#include <Poco/Net/HTTPServerRequest.h>
#include <Poco/Net/HTTPServerResponse.h>

// Handly creation macros
#define MAPPING(vec, method, rex, member) vec.push_front(Mapping{method, boost::xpressive::sregex::compile(rex), \
    std::bind(&member, this, std::placeholders::_1, std::placeholders::_2, std::placeholders::_3)});
#define MAPPING_GET(vec, rex, member) MAPPING(vec, Poco::Net::HTTPServerRequest::HTTP_GET, rex, member)
#define MAPPING_POST(vec, rex, member) MAPPING(vec, Poco::Net::HTTPServerRequest::HTTP_POST, rex, member)
#define MAPPING_PUT(vec, rex, member) MAPPING(vec, Poco::Net::HTTPServerRequest::HTTP_PUT, rex, member)
#define MAPPING_DELETE(vec, rex, member) MAPPING(vec, Poco::Net::HTTPServerRequest::HTTP_DELETE, rex, member)
#define MAPPING_OPTIONS(vec, rex, member) MAPPING(vec, Poco::Net::HTTPServerRequest::HTTP_OPTIONS, rex, member)

// Dependencies
class Controller;

/** URL Mapping to controller. */
class Mapping
{
public:
    const std::string& method; /**< @brief Request method, like POST or GET. */
    const boost::xpressive::sregex rex; /**< @brief Regex to match. */
    /** @brief Function to call. */
    std::function<bool(Poco::Net::HTTPServerRequest&, Poco::Net::HTTPServerResponse&, boost::xpressive::smatch&)> func;
};

#endif // MAPPING_H
