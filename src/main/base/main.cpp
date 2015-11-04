#include <iostream>
#include <base/ServerApp.h>

/** Main entry point. Starts Poco server. */
int main(int argc, char** argv)
{
    try {
        ServerApp app;
        return app.run(argc, argv);
    } catch(Poco::Exception& exc) {
        std::cerr << exc.displayText() << std::endl;
        return Poco::Util::Application::EXIT_SOFTWARE;
    } catch(...) {
        std::cerr << "Unexpected happened." << std::endl;
        return Poco::Util::Application::EXIT_SOFTWARE;
    }
}
