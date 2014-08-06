package nl.vinyamar.cricforce.backend.util

class NetUtils {

    static freePorts(int n) {
        def ports = []
        def servers = []
        for (int i = 0; i < n; i++) {
            def server = new ServerSocket(0)
            ports.add(server.getLocalPort())
            servers.add(server)
        }
        servers.each {it.close()}
        ports
    }
}
