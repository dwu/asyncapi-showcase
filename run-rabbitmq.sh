#!/bin/bash

podman run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 docker.io/rabbitmq:3-management
