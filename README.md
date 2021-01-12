
library application
===============================

Cette application est basée sur SpringBoot et sa structure a été généré via l'archetype maven.


Elle est composée d'un projet parent et de 5 sous-modules :

- library-domain
- library-application
- library-exposition
- library-infrastructure
et
- library-batch

La couche exposition utilise [OpenAPI-Spec](https://github.com/swagger-api/swagger-core) et [Springfox](https://github.com/springfox/springfox) afin de documenter les services exposés.
(Elle est optionnelle, supprimer ce projet n'expose pas une API)

La couche batch utilise Spring-Batch
(Elle est optionnelle, supprimer ce projet si l'application ne comprend pas de batch)


# Démarrer l'application Web

1) Depuis maven dans le projet "library-exposition" :

	mvn clean spring-boot:run

2) Depuis Eclipse dans le projet "library-exposition" :

classe : fr.training.spring.exposition.LibraryAppApplication

Run as java application

	url : http://localhost:8080/swagger-ui.html


# Lancer le batch

Depuis une fenêtre de commande :

dans le projet "library-batch" :

java -jar  target${rootArtifactId}-batch-x.y.jar