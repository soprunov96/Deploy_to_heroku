web:    java $JAVA_OPTS -jar target/dependency/* --port $PORT target/*.war
heroku config:set WEBAPP_RUNNER_OPTS="--enable-naming --context-xml=WebContent/META-INF/context.xml"