[![Build Status](https://travis-ci.com/0gener/calculator-service.svg?branch=main)](https://travis-ci.com/0gener/calculator-service)
[![codecov](https://codecov.io/gh/0gener/calculator-service/branch/main/graph/badge.svg?token=1M387PO5SC)](https://codecov.io/gh/0gener/calculator-service)

<!-- ABOUT THE PROJECT -->
## About 
Calculator Service is a simple webservice with only one endpoint that performs a calculation.

### Built With

* Java
* Spring Boot

<!-- GETTING STARTED -->
## Getting Started

### Run locally

1. Clone the repo
   ```sh
   git clone https://github.com/0gener/calculator-service.git
   ```
2. cd into the repo
   ```sh
   cd calculator-service
   ```
3. Run using docker-compose
   ```sh
   docker-compose up -d --build
   ```

<!-- CI/CD -->
## CI/CD
As you can see from the badges at the top of this README.me file, this project is using **Travis CI** for continuous integration and **codecov** to share the coverage report.
It is also integrated with **Heroku** for when all the CI/CD jobs succeed, it automatically deploys the project and can the accessible [here](https://ogener-calculator-service.herokuapp.com).
