#include <base/ServerApp.h>
#include <base/Globals.h>
#include <Poco/Util/LoggingConfigurator.h>
#include <Poco/Net/HTTPServer.h>
#include <base/RequestMapper.h>
#include <controller/RootController.h>

/** Main entry point for the server application. */
int ServerApp::main(const std::vector<std::string>& args)
{
    // read configurations
    configs = new Poco::Util::PropertyFileConfiguration("config.prop");

    // setup logging
    Poco::Util::LoggingConfigurator configurator;
    configurator.configure(configs);

    // set up HTTPServer
    Poco::Net::ServerSocket svs(configs->getInt("serverPort"));
    Poco::Net::HTTPServer srv(new RequestMapper{
                                  std::make_shared<RootController>()
                              }, svs, new Poco::Net::HTTPServerParams);

    // start the HTTPServer
    srv.start();

    // wait for ctrl-c or kill
    waitForTerminationRequest();

    // stop all services
    srv.stop();

    // report status
    return Application::EXIT_OK;
}
