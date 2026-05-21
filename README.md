# Nailstore Webshop

The goal of this project is to build a simple, user-friendly online store focused on nail care products with features like product browsing, searching, and order management.

## Current Status

The project has evolved significantly from its initial setup and now includes several working features:

### Implemented Features

- **Product Management**: Complete product listing with images, descriptions, and prices
- **Product Details Page**: Individual product pages with detailed information
- **Advanced Search & Filtering**:
    - Live search functionality
    - Category-based filtering
    - Tag-based filtering system
    - Multiple sorting options (name, price - ascending/descending)
- **Shopping Cart**: Full cart functionality with add/remove/update operations
- **Responsive Design**: Modern, mobile-first design with consistent styling across all pages
- **User Interface**: Clean, professional design using DaisyUI and Tailwind CSS

### In Progress

- User authentication and authorization
- Contact form functionality
- Order placement system (in-store pickup)

### Planned Features

- Admin panel for managing products and orders
- Order history and tracking
- Email notifications

## Technologies Used

- **Backend**: Java 17, Spring Boot, Spring Data JPA
- **Frontend**: Thymeleaf, HTML5, CSS3
- **Styling**: Tailwind CSS, DaisyUI
- **Database**: MySQL
- **Build Tool**: Maven

## Project Structure

```
src/main/
├── java/
│   └── com/nailstore/
│       ├── controller/    # Spring MVC controllers
│       ├── model/         # Entity classes
│       ├── repository/    # Data access layer
│       └── service/       # Business logic
├── resources/
│   ├── static/           # CSS, JS, images
│   ├── templates/        # Thymeleaf templates
│   └── application.properties
```

## Getting Started

1. Clone the repository
```bash
git clone [repository-url]
cd nailstore-webshop
```

2. Configure your `application.properties` with your database credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nailstore
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. Build and run the project with Maven:
```bash
mvn clean install
mvn spring-boot:run
```

4. Access the application at `http://localhost:8080`
