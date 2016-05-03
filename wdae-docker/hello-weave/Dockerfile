FROM java:8

RUN wget http://downloads.lightbend.com/scala/2.11.8/scala-2.11.8.tgz \
  && tar xzf scala-2.11.8.tgz \
  && rm -f scala-2.11.8.tgz

ENV SCALA_HOME 	/scala-2.11.8
ENV PATH ${PATH}:${SCALA_HOME}/bin

COPY tmp/hello-1.0.zip .

COPY entrypoint.sh .

RUN unzip hello-1.0.zip && rm hello-1.0.zip

ENTRYPOINT ["/entrypoint.sh"]
