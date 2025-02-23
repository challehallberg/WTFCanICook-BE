# Recipe Backend Application

This project is a Spring Boot backend application that integrates with external APIs (Spoonacular and OpenAI) to process images and retrieve enriched recipe information. The application exposes REST endpoints for uploading images, searching for recipes by ingredients, and retrieving detailed instructions. It also features caching with Caffeine and is containerized using Docker with OpenJDK 21.

## Features

- **Image Processing:**  
  Upload one or more image files. The backend processes images using OpenAI's API (ChatGPT) to extract ingredients.

- **Recipe Search:**  
  Retrieve recipes from the Spoonacular API based on extracted ingredients.
    - Searches by ingredients.
    - Bulk retrieval of detailed recipe information.
    - Enrichment of recipe details (e.g., ready time, dietary flags).

- **Caching:**  
  Uses Caffeine for in-memory caching with expiration (e.g., cache entries clear after 10 minutes).

- **Error Handling:**  
  A global REST exception handler returns consistent HTTP responses (e.g., 400 Bad Request, 500 Internal Server Error).

- **API Documentation:**  
  Swagger/OpenAPI annotations are used (disabled in production via properties).

- **Deployment:**  
  Containerized using a multi-stage Dockerfile with OpenJDK 21.  

## Prerequisites

- **Java:** OpenJDK 21
- **Build Tool:** Maven
- **Docker:** For containerization (optional)
- **API Keys:**
    - Spoonacular API Key
    - OpenAI API Key (for ChatGPT image processing)

## Environment Configuration

### Using Environment Variables

For security, API keys are not stored in source control. Instead, reference them via environment variables.

In your `application.properties` (or a profile-specific file), include:
```properties
spoonacular.api.key=${SPOONACULAR_API_KEY}
openai.api.key=${OPENAI_API_KEY}
```

## Future iterations

- **Advanced Search:**  
   Enhance the recipe search functionality by integrating Spoonacular’s advanced search filters. Future updates will allow users to refine their queries based on dietary restrictions (e.g., vegetarian, vegan), cuisine types (e.g., Italian, Mexican), and other specific preferences. This will empower users to find recipes that best match their tastes and nutritional needs.
- **Cache Update to Redis:**  
   As the application scales and multiple backend instances are deployed, using an in-memory cache like Caffeine may become insufficient. Future iterations will replace the current cache with Redis, a distributed caching solution, ensuring consistent and optimal performance across all instances while supporting advanced caching features like persistence and TTL.
- **Local Grocery Store Integration:**  
   To make the application even more practical, upcoming features will include the ability to locate nearby grocery stores that carry the missing ingredients for a specific recipe. This integration will help users quickly identify where they can purchase the items needed to complete a recipe, bridging the gap between digital recipes and real-world grocery shopping.
 