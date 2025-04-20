public enum HttpStatus {
    OK(200, "успешно"),
    BAD_REQUEST(400, "некорректный запрос"),
    NOT_FOUND(404, "не найдено"),
    INTERNAL_SERVER_ERROR(500, "внутренняя ошибка сервера"),
    NO_ACCESS(403, "недостаточно прав"),
    UNKONWN(0, "неизвестный статус");

    private final int code;
    private final String description;

    HttpStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static HttpStatus getFromCode(int code) {
        for (HttpStatus status : values()) {
            if (status.code == code)
                return status;
        }
        return UNKONWN;
    }
}
