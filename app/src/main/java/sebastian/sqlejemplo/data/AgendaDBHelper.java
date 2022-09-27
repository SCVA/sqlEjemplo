package sebastian.sqlejemplo.data;

import static sebastian.sqlejemplo.data.UsuarioContract.UsuarioEntry;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AgendaDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Agenda.db";

    public AgendaDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "CREATE TABLE " + UsuarioEntry.TABLE_NAME + " ("
                + UsuarioEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + UsuarioEntry.NAME + " TEXT NOT NULL,"
                + UsuarioEntry.PASSWORD + " INTEGER NOT NULL)");
    }

    public long guardarUsuario(Usuario usuario){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                UsuarioEntry.TABLE_NAME,
                null,
                usuario.toContentValues());
    }

    public Cursor getAllUsuarios(){
        return getReadableDatabase()
                .query(
                        UsuarioEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }

    public Cursor getUsuarioById(String usuarioID) {
        Cursor c = getReadableDatabase().query(
                UsuarioEntry.TABLE_NAME,
                null,
                UsuarioEntry._ID + " LIKE ?",
                new String[]{usuarioID},
                null,
                null,
                null);
        return c;
    }

    public int eliminarUsuario(String nombreUsuario){
        return getWritableDatabase().delete(
                UsuarioEntry.TABLE_NAME,
                UsuarioEntry.NAME + " LIKE ?",
                new String[]{nombreUsuario});
    }

    public int actualizarUsuario(Usuario usuarioActualizado,String nombreUsuario){
        return getWritableDatabase().update(
                UsuarioEntry.TABLE_NAME,
                usuarioActualizado.toContentValues(),
                UsuarioEntry.NAME + " LIKE ?",
                new String[]{nombreUsuario}
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
