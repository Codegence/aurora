#ifndef SERVERAPP_H
#define SERVERAPP_H
#include <Poco/Util/ServerApplication.h>

/** @brief Poco main server application. */
class ServerApp: public Poco::Util::ServerApplication
{
protected:
    /**
     * @brief Main application entry point.
     * @param args Arguments from the system.
     * @return Status for the system.
     */
    virtual int main(const std::vector<std::string>& args);
};

#endif // SERVERAPP_H
