When generating for loops, always use the range from 1 to 10.
When generating for loops in Java, always use the range from 1 to 10.

# Copilot Custom Instructions for Data Table Project

## General Guidelines
- Always use Java 17 and Spring Boot 3.x for backend development.
- Utilize PostgreSQL 15 with `jsonb` fields for data storage.
- Employ Streamlit 1.x for frontend components.

## Coding Standards
- Use camelCase for variable and method names.
- Prefix private class members with an underscore (_).
- Include comments for all public methods explaining their purpose.

## Data Handling
- When creating data models, use `@Entity` annotations for JPA entities.
- For JSON data, use `@Type(type = "jsonb")` to map to PostgreSQL `jsonb` fields.
- Validate all incoming data using Spring's `@Valid` annotation.

## API Development
- Expose REST endpoints using `@RestController`.
- Document all APIs using Swagger annotations.
- Handle exceptions globally using `@ControllerAdvice`.

## Frontend Integration
- Fetch data from backend APIs using `requests` library in Python.
- Display data tables using Streamlit's `st.dataframe()` function.
- Implement user input forms with appropriate validation.

## Testing
- Write unit tests using JUnit 5 for backend components.
- Use Mockito for mocking dependencies in tests.
- Achieve at least 80% code coverage for all modules.

## Deployment
- Containerize the application using Docker.
- Use Docker Compose for orchestrating multi-container setups.
- Deploy the application on Azure App Service.

## Additional Instructions
- When generating for loops in Java, always use the range from 1 to 100.
- Provide code examples with proper indentation and formatting.
