package sebastian.sqlejemplo.data;

import android.content.ContentValues;
import android.database.Cursor;
import sebastian.sqlejemplo.data.UsuarioContract.UsuarioEntry;

public class Usuario {

    private String name;
    private Integer password;


    public Usuario(String name, Integer password) {
        this.name = name;
        this.password = password;
    }

    public Usuario(Cursor cursor){
        //System.out.println(cursor.toString());
        name = cursor.getString(cursor.getColumnIndex(UsuarioEntry.NAME));
        password = cursor.getInt(cursor.getColumnIndex( UsuarioEntry.PASSWORD ) );
    }

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();
        values.put(UsuarioEntry.NAME, name);
        values.put(UsuarioEntry.PASSWORD, password);
        return values;
    }


    public String getName() {
        return name;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }
}
