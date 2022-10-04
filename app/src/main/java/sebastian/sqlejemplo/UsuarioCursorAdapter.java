package sebastian.sqlejemplo;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import sebastian.sqlejemplo.data.UsuarioContract;

public class UsuarioCursorAdapter extends CursorAdapter {

    public UsuarioCursorAdapter(Context context, Cursor objects){
        super(context,objects,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.list_item_usuarios, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameList = (TextView) view.findViewById( R.id.listNamePassword );
        TextView passwordList = (TextView) view.findViewById( R.id.listPasswordUser );

        String name = cursor.getString(cursor.getColumnIndex( UsuarioContract.UsuarioEntry.NAME));
        Integer password = cursor.getInt(cursor.getColumnIndex( UsuarioContract.UsuarioEntry.PASSWORD ) );

        nameList.setText( name );
        passwordList.setText( password.toString() );

    }
}
