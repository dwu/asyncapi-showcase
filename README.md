# Scenario

![](./doc/diagram.png)

# Run

- Start a local rabbitmq broker, e.g. via `./run-rabbitmq.sh` or similar; the broker is expected to
  listen on `localhost:5672`
- Start shop, delivery and payment via `mvn spring-boot:run`
- Create orders via `curl -X POST http://localhost:8080/create-order`

# Contents

- `asyncapi/shared.yml`: Contains API description elements shared among the 3 services *shop*,
  *payment* and *delivery*
- `{shop,payment,delivery}`: Spring Boot services
- `{shop,payment,delivery}/modelina`: [Modelina](https://github.com/asyncapi/modelina) generator
  script for model generation

<!-- vim: set tw=100: -->
