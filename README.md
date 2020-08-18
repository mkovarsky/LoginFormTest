### Запуск тестов.
1. Установить [OpenJDK11](https://adoptopenjdk.net/);
2. Установить [IntelliJ IDEA](https://www.jetbrains.com/idea/download/);
3. Скачать [архив](https://github.com/mkovarsky/LoginFormTest/archive/master.zip);
4. Открыть загруженные файлы в среде разработки IntelliJ IDEA (File -> Open...).

Запустить тесты командой:
```
gradlew clean test allureReport 
```
    
Для просмотра отчета выполнить команду:
```
gradlew allureServe
```
    
*для удобства выполнения команд можно воспользоваться шорткатом Double Ctrl
