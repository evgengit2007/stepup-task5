package ru.stepup.course2.stepuptask5.interfaces;

import java.util.List;

public interface CreateRecordsInt {
    // добавить одну запись
    <T, M> T addRecordTable(M model);

    // добавить несколько записей
    <T, M> List<T> addListRecordsTable(M model);
}
