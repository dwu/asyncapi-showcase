import { JAVA_JACKSON_PRESET, JavaFileGenerator } from '@asyncapi/modelina';
import path from 'path';
import * as fs from 'fs';

const ASYNCAPI = "../src/main/resources/asyncapi/api-delivery.yml";
const PACKAGE_NAME = 'com/example/delivery';

const MODEL_DIR = `../target/generated-sources/asyncapi/${PACKAGE_NAME}`;

const generator = new JavaFileGenerator({
  presets: [JAVA_JACKSON_PRESET]
});

const input = fs.readFileSync(ASYNCAPI).toString();
generator.generateToFiles(input, MODEL_DIR, {
  packageName: 'com.example.shop'
});