![Project image](./assets/Bild.jpeg)

# CRUD JAKARTA EE
___

**Dieses Projekt wurde mit Jakarta EE entwickelt, um meine grundlegenden Programmierfähigkeiten zu demonstrieren. Dabei habe ich grundlegende Konzepte dieser Plattform in die Praxis umgesetzt, was meine Fähigkeit zeigt, mit modernen Technologien zu arbeiten, und meine Bereitschaft, weiter zu lernen und mich zu verbessern.**
___
## Struktur 💻
Mein Projekt ist wie folgt strukturiert:
#### 1. `Model:`In diesem Paket befinden sich die Entitäten der Anwendung. Die Entitäten sind wie folgt:
#### Course

```java
@Entity
@Table(name = "courses")
public class Course  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private String teacher;
    @Column(name = "date_start")
    private LocalDate dateStart;
    @Column(name = "date_end")
    private LocalDate dateEnd;

```

#### 2. `Controller:` Dieses Paket enthält den Controller, der die Endpunkte meiner API-REST verwaltet. Die verfügbaren Endpunkte sind wie folgt:

##### Host: localhost:8080

 * `[GET]: /CrudJakarta/api/courses/find`: Dieser Endpoint listet alle Kurse in der Datenbank auf.
 * `[GET]: /CrudJakarta/api/courses/find/{id}`: Dieser Endpoint ermöglicht die Suche nach einem bestimmten Kurs anhand der ID.
 * `[POST]: /CrudJakarta/api/courses/save`:Dieser Endpoint ermöglicht das Hinzufügen eines neuen Kurses in die Datenbank.
 * `[PUT]: /CrudJakarta/api/courses/update/{id}`: Dieser Endpoint dient dazu, die Daten eines bestehenden Kurses in der Datenbank zu aktualisieren.
 * `[DELETE]: /CrudJakarta/api/courses/delete/{id}`: Dieser Endpoint ermöglicht das Löschen eines Kurses aus der Datenbank anhand der ID.

#### 3. `Persistence:` Hier befinden sich die Konfigurationen unseres EntityManager sowie die Repositories der Anwendung."
 
#### EntityManager
```java
 @PersistenceContext(name = "persistenceContext")
 private EntityManager entityManager;
```

#### Repository
```java
 public interface ICourseRepository {

    List<Course> findAll();
    
    Course findById(Long id);

    void save(Course course);

    void deleteById(Course course);
}
```

#### 4. `Service:` In diesem Paket befindet sich die Geschäftslogik der Anwendung.
##### Service
```java
 public interface ICourseService {
    List<Course> findAll();
    
    Optional<Course> findById(Long id);
    
    void save(Course course);
    
    void deleteById(Course id);
}
```
___

## Konfiguration: 
**Dieses Projekt verwaltet zwei Konfigurationsdateien. Eine für die Verbindung zur Datenbank und eine andere für die Konfiguration des Servlet-Kontexts.**

#### Persintence Konfiguration mit der Datei `persistence.xml`

```xml
<properties>
    <!-- Konfiguration des Persistenzanbieters -->
    <property name="jakarta.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver" />
    <property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/<Datenbank>" />
    <property name="jakarta.persistence.jdbc.user" value="<Benutzer>" />
    <property name="jakarta.persistence.jdbc.password" value="<passwort>" />

    <!-- Konfiguration von Hibernate als JPA-Anbieter -->
    <property name="hibernate.dialect" value="org.hibernate.dialect.MySQLDialect" />
    <property name="hibernate.show_sql" value="true" />
    <property name="hibernate.format_sql" value="true" />
</properties>
```

#### Konfiguration des Servlet-Konzepts in der Datei `web.xml´
```xml
<web-app xmlns="https://jakarta.ee/xml/ns/jakartaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee https://jakarta.ee/xml/ns/jakartaee/web-app_6_0.xsd"
         version="6.0">

    <!-- Configuración del contexto del servlet -->
    <servlet>
        <servlet-name>jakarta.ws.rs.core.Application</servlet-name>
    </servlet>

    <!-- Mapeo del servlet -->
    <servlet-mapping>
        <servlet-name>jakarta.ws.rs.core.Application</servlet-name>
        <url-pattern>/api/*</url-pattern>
    </servlet-mapping>
</web-app>
```
