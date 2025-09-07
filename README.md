# Scenario

![](./doc/diagram.png)

# Run

- Start a local rabbitmq broker, e.g. via `./run-rabbitmq.sh` or similar; the broker is expected to
  listen on `localhost:5672`
- Start shop, delivery and payment via `mvn spring-boot:run`
- Create orders via `curl -X POST http://localhost:8080/create-order`

<!-- vim: set tw=100: -->
