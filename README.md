# Edu DevOps
W celu zrealizowania edu została przygotowana prosta aplikacja webowa z wykorzystaniem spring-boota łącząca
się do bazy postgresql. Posiada dwa endpointy:
1. "/" - wyświetla dane z tabeli message z bazy danych
2. "/kubernetes" - wyświetla nazwe poda do którego został wysłany request

---

## Docker
Aplikacja została skonteneryzowana z wykorzystaniem dockera.
W pliku `Dockerfile` znajduje się dockerfile, który buduje oraz uruchamia aplikację webową.
W pliku `docker-compose` znajduje się compose dla postawienia bazy wraz z aplikacją.

(Wymagany zainstalowany docker)

Kroki w celu odtworzenia:
1. Stworzenie network do komunikacja aplikacja -> baza\
`docker network create devops`
2. Uruchomienie bazy danych postgresql \
`docker run -p 5432:5432 --name devops-postgres -e POSTGRES_PASSWORD=root -e POSTGRES_USER=root -e POSTGRES_DB=postgres -d --network devops postgres`
3. Zbudowanie obrazu aplikacji\
`docker build -t webapp .`
4. Uruchomienie kontenera\
`docker run --network devops --name webapp -p 9000:9000 -d webapp`

Kroki w celu odtworzenia z wykorzystanie docker-compose:
1. `docker-compose up -d`

---

## Jenkins
Został stworzony pipeline CI/CD z wykorzystaniem lokalnie postawionego Jenkinsa.
Konfiguracja pipelinea znajduje się w pliku `Jenkinsfile`. 
Repozytorium zostało również wrzucone pod adresem https://github.com/lszypski/WebApp

Kroki w celu odtworzenia (wymagana baza posgresql):
1. Instalacja Jenkinsa\
W folderze `src/main/resources/jenkins` uruchomić polecenie\
`docker run -p 8080:8080 -p 50000:50000 -p 9000:9000 -d --name jenkins --network devops jenkins-maven`
2. Konfiguracja Jenkinsa adres http://localhost:8080 (stworzenie konta, zainstalowanie rekomendowanych pluginów)
3. Stworzenie pipelinea `webapp-pipeline`\
Należy ustawić opcję `Pipeline script from SCM` oraz podać link do repozytorium z githuba
4. Kliknąć `Build now` (pipeline zostanie uruchomiony, a aplikacja zdeployowana pod adresem
http://localhost:9000)

---

## Kubernetes
Zostały przygotowane pliki konfiguracyjne dla postawienia aplikacji za pomocą kubernetesa.
W tym same pody lub z wykorzystaniem deployment oraz serwisy do komunikacji między podami i użytkownikiem.
Znajdują się one w folderze `src/main/resources/kubernetes`

(Wymagany zainstalowany kubernetes (w Windowsie można wykorzystać wbudowny w docker desktop))

Kroki w celu odtworzenia (pody + serwisy). W folderze z plikami do kubernetesa:
1. Stworzyć serwisy\
`kubectl create -f postgres-service-kb.yaml`\
`kubectl create -f webapp-service-kb.yaml`
2. Stworzyć pody\
`kubectl create -f postgres-kb.yaml`\
`kubectl create -f webapp-kb.yaml`\
3. Aplikacja zostanie uruchomina pod adresem http://localhost:9000

Kroki w celu odtworzenia (deployment + serwisy). W folderze z plikami do kubernetesa:
1. Stworzyć serwisy\
`kubectl create -f postgres-service-kb.yaml`\
`kubectl create -f webapp-service-kb.yaml`
2. Stworzyć deploymenty\
`kubectl create -f postgres-deployment-kb.yaml`\
`kubectl create -f webapp-deployment-kb.yaml`
3. Aplikacja zostanie uruchomina pod adresem http://localhost:30001. Aplikacja oraz baza posiadają
po dwie repliki, dzielące requesty między sobą, co można zaobserwować poprzez komendę
`curl http://localhost:30001/kubernetes`, która
zwraca nazwe zapytanego poda.
