package com.xunhuan.zuoai.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.xunhuan.zuoai.data.local.entity.TaskRecord;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class TaskRecordDao_Impl implements TaskRecordDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TaskRecord> __insertionAdapterOfTaskRecord;

  public TaskRecordDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTaskRecord = new EntityInsertionAdapter<TaskRecord>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `task_records` (`id`,`taskId`,`taskTitle`,`completedAt`,`moodScore`,`flowScore`,`repeatScore`,`note`,`pointsEarned`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TaskRecord entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTaskId());
        statement.bindString(3, entity.getTaskTitle());
        statement.bindLong(4, entity.getCompletedAt());
        statement.bindLong(5, entity.getMoodScore());
        statement.bindLong(6, entity.getFlowScore());
        statement.bindLong(7, entity.getRepeatScore());
        statement.bindString(8, entity.getNote());
        statement.bindLong(9, entity.getPointsEarned());
      }
    };
  }

  @Override
  public Object insert(final TaskRecord record, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTaskRecord.insert(record);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<TaskRecord>> getAllRecords() {
    final String _sql = "SELECT * FROM task_records ORDER BY completedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"task_records"}, new Callable<List<TaskRecord>>() {
      @Override
      @NonNull
      public List<TaskRecord> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTaskId = CursorUtil.getColumnIndexOrThrow(_cursor, "taskId");
          final int _cursorIndexOfTaskTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "taskTitle");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfMoodScore = CursorUtil.getColumnIndexOrThrow(_cursor, "moodScore");
          final int _cursorIndexOfFlowScore = CursorUtil.getColumnIndexOrThrow(_cursor, "flowScore");
          final int _cursorIndexOfRepeatScore = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatScore");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final int _cursorIndexOfPointsEarned = CursorUtil.getColumnIndexOrThrow(_cursor, "pointsEarned");
          final List<TaskRecord> _result = new ArrayList<TaskRecord>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TaskRecord _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTaskId;
            _tmpTaskId = _cursor.getString(_cursorIndexOfTaskId);
            final String _tmpTaskTitle;
            _tmpTaskTitle = _cursor.getString(_cursorIndexOfTaskTitle);
            final long _tmpCompletedAt;
            _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            final int _tmpMoodScore;
            _tmpMoodScore = _cursor.getInt(_cursorIndexOfMoodScore);
            final int _tmpFlowScore;
            _tmpFlowScore = _cursor.getInt(_cursorIndexOfFlowScore);
            final int _tmpRepeatScore;
            _tmpRepeatScore = _cursor.getInt(_cursorIndexOfRepeatScore);
            final String _tmpNote;
            _tmpNote = _cursor.getString(_cursorIndexOfNote);
            final int _tmpPointsEarned;
            _tmpPointsEarned = _cursor.getInt(_cursorIndexOfPointsEarned);
            _item = new TaskRecord(_tmpId,_tmpTaskId,_tmpTaskTitle,_tmpCompletedAt,_tmpMoodScore,_tmpFlowScore,_tmpRepeatScore,_tmpNote,_tmpPointsEarned);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<TaskRecord>> getRecentRecords(final int limit) {
    final String _sql = "SELECT * FROM task_records ORDER BY completedAt DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"task_records"}, new Callable<List<TaskRecord>>() {
      @Override
      @NonNull
      public List<TaskRecord> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTaskId = CursorUtil.getColumnIndexOrThrow(_cursor, "taskId");
          final int _cursorIndexOfTaskTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "taskTitle");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfMoodScore = CursorUtil.getColumnIndexOrThrow(_cursor, "moodScore");
          final int _cursorIndexOfFlowScore = CursorUtil.getColumnIndexOrThrow(_cursor, "flowScore");
          final int _cursorIndexOfRepeatScore = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatScore");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final int _cursorIndexOfPointsEarned = CursorUtil.getColumnIndexOrThrow(_cursor, "pointsEarned");
          final List<TaskRecord> _result = new ArrayList<TaskRecord>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TaskRecord _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTaskId;
            _tmpTaskId = _cursor.getString(_cursorIndexOfTaskId);
            final String _tmpTaskTitle;
            _tmpTaskTitle = _cursor.getString(_cursorIndexOfTaskTitle);
            final long _tmpCompletedAt;
            _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            final int _tmpMoodScore;
            _tmpMoodScore = _cursor.getInt(_cursorIndexOfMoodScore);
            final int _tmpFlowScore;
            _tmpFlowScore = _cursor.getInt(_cursorIndexOfFlowScore);
            final int _tmpRepeatScore;
            _tmpRepeatScore = _cursor.getInt(_cursorIndexOfRepeatScore);
            final String _tmpNote;
            _tmpNote = _cursor.getString(_cursorIndexOfNote);
            final int _tmpPointsEarned;
            _tmpPointsEarned = _cursor.getInt(_cursorIndexOfPointsEarned);
            _item = new TaskRecord(_tmpId,_tmpTaskId,_tmpTaskTitle,_tmpCompletedAt,_tmpMoodScore,_tmpFlowScore,_tmpRepeatScore,_tmpNote,_tmpPointsEarned);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<CategoryCount>> getCountByCategory() {
    final String _sql = "\n"
            + "        SELECT t.category, COUNT(r.id) as count\n"
            + "        FROM task_records r\n"
            + "        INNER JOIN tasks t ON r.taskId = t.id\n"
            + "        GROUP BY t.category\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"task_records",
        "tasks"}, new Callable<List<CategoryCount>>() {
      @Override
      @NonNull
      public List<CategoryCount> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCategory = 0;
          final int _cursorIndexOfCount = 1;
          final List<CategoryCount> _result = new ArrayList<CategoryCount>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CategoryCount _item;
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final int _tmpCount;
            _tmpCount = _cursor.getInt(_cursorIndexOfCount);
            _item = new CategoryCount(_tmpCategory,_tmpCount);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<Integer> getTotalCount() {
    final String _sql = "SELECT COUNT(*) FROM task_records";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"task_records"}, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
