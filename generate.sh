#!/bin/bash

function generate() {
	NAME=$1

	echo -n "Generating ${NAME}..."
    if [[ -d ${NAME} ]]; then echo " directory exists... skipping."; return; fi

	ag \
		api-${NAME}.yml \
		@asyncapi/java-spring-cloud-stream-template \
		-p binder=rabbit \
		-p artifactId=${NAME} \
		-p groupId=com.example \
		-p javaPackage=com.example.${NAME} \
		-o ${NAME} \
		--force-write
    echo " done."
}

function doc() {
    NAME=$1

    echo -n "Generating documentation for ${NAME}..."

	ag \
		api-${NAME}.yml \
		@asyncapi/html-template \
        -p singleFile=true \
		-p outFilename=api-${NAME}.html \
        --force-write 1>/dev/null
    echo " done."
}

generate "shop"
doc "shop"

generate "delivery"
doc "delivery"

generate "payment"
doc "payment"
