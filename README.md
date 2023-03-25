# pg-taglets-java

## About

Javadoc module providing `assert`, `apiNote`, `implNote`, and `implSpec` taglets.

## License

Apache License, Version 2.0

## Requirements

### Runtime

1. Java 17 or above.

### Building

1. Gradle 8 or above.

## Building

To build the JAR file, invoke the `jar` task:

```
./gradlew jar
```

To publish to local maven repository, invoke the `publishToMavenLocal` task:

```
./gradlew publishToMavenLocal
```

## Usage

This module introduces 4 new block tags:

1. `@assert <note>` - Precondition required to hold true apriori method call, or for an instance.
2. `@apiNote <note>` - Describes additional API details to end-user.
3. `@implNote <note>` - Describes implementation specific details to end-user.
4. `@implSpec <note>` - Describes bare-minimum implementation requirements to end-user.

Each tag may be specified more than once per element being documented. If there is a single
occurrence of a tag, then its note is printed as-is in a `dd` HTML tag. Otherwise, when a tag is
specified more than once, notes are sorted in an unordered HTML list `ul`, and each note is placed
inside its own element `li`.

```java
public interface Foo {

    /**
     * Test whether this has a valid state.
     *
     * @return {@code true} <i>if and only if</i> state is valid.
     * @since 1.0
     */
    boolean isValid();

    /**
     * Do bar for foo (unsafe).
     *
     * @assert {@code this} must have a {@linkplain #isValid() valid state}.
     * @assert {@code 0 <= baz <= 30}
     * @param baz Baz to bar with.
     * @since 1.0
     * @see #bar(int)
     */
    void unsafeBar(int baz);

    /**
     * Do bar for foo.
     *
     * @apiNote Some note about this API.
     * @implSpec Base implementation requirements.
     * @param baz Baz to bar with.
     * @throws IllegalStateException {@code this} does not have a {@linkplain #isValid() valid state}.
     * @throws IllegalArgumentException {@code baz} is negative or greater than {@code 30}.
     * @since 1.0
     */
    void bar(int baz);
}
```

### Maven

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-javadoc-plugin</artifactId>
    <version>3.5.0</version>
    <configuration>
        ...
        <taglets>
            <taglet>
                <tagletClass>org.polygamma.taglets.ApiNoteTaglet</tagletClass>
            </taglet>
            <taglet>
                <tagletClass>org.polygamma.taglets.AssertTaglet</tagletClass>
            </taglet>
            <taglet>
                <tagletClass>org.polygamma.taglets.ImplNoteTaglet</tagletClass>
            </taglet>
            <taglet>
                <tagletClass>org.polygamma.taglets.ImplSpecTaglet</tagletClass>
            </taglet>
        </taglets>
        <tagletArtifact>
            <groupId>org.poly-gamma</groupId>
            <artifactId>pg-taglets</groupId>
            <version>1.0.0</version>
        </tagletArtifact>
    </configuration>
</plugin>
```

### Gradle

```groovy
repositories {
    mavenCentral()
}

configurations {
    javadocTaglets
}

dependencies {
    javadocTaglets('org.poly-gamma:pg-taglets:1.0.0')
}

tasks.withType(Javadoc).configureEach {
    options.tagletPath = configurations.javadocTaglets.files.asType(List)
    options.taglets = [
        'org.polygamma.taglets.ApiNoteTaglet',
        'org.polygamma.taglets.AssertTaglet',
        'org.polygamma.taglets.ImplNoteTaglet',
        'org.polygamma.taglets.ImplSpecTaglet'
    ]
}
```
