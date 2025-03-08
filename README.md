## Reactive Websocket

Thes Websocket service provides real-time communication using Spring WebFlux. It exposes two endpoints:
1. **"/neon"** [Main endpoint]
2. **"/nexus"** [Additional Endpoint]

Clients such as PostMan should have no problem connecting to this endpoints

#### N.B: Do use the port number defind in the application.properties file

### Connection

##### Method 1:
To connect to the Websocket , make sure you have installed PostMan on your local machine

You can connect to the following endpoints
1. **"/neon"** [Main endpoint]
2. **"/nexus"** [Additional Endpoint]

Once you have been connected messages acan be sent to the websocket and an Echo would be returned
