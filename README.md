# FWebScanner
WebScanner that can check sites by their addresses. This project was created to learn Multithreading.

# Project overview 
The program uses multithreading to process multiple URLs simultaneously. It creates 10 worker threads to scan websites in parallel, reducing total execution time compared to sequential processing.

#### HTTP status codes (standard responses):

200 – OK (успешно)

400 – bad request (некорректный запрос)

403 – no access (недостаточно прав)

404 – not found (не найдено)

500 - Internal Server Error (внутренняя ошибка сервера)

-999 – not available (недоступно в России)

# How to use
1. Add target website afressess (one per line) in URLlist file.
2. Run the program.
3. After completion, check results with code and description in log file.
# Key features
* Parallel Processing
* Error Handling
* Simple setup
