package sebastian.sqlejemplo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import sebastian.sqlejemplo.data.AgendaDBHelper;
import sebastian.sqlejemplo.data.Usuario;

public class MainActivity extends AppCompatActivity {

    AgendaDBHelper dbAyudante;
    //UsuarioAdapater usuarioAdaptador;
    UsuarioCursorAdapter usuarioAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        //setContentView( R.layout.activity_main_v2 );
        Button btnGuardar = (Button) findViewById( R.id.btnGuardar );
        EditText nombreTxt = (EditText) findViewById( R.id.usrText );
        EditText passwordTxt = (EditText) findViewById( R.id.passwordText );
        ListView milista = (ListView) findViewById( R.id.listaUsuarios );
        //RecyclerView milista = (RecyclerView) findViewById( R.id.recyclerListusuarios );
        dbAyudante = new AgendaDBHelper(this);
        /*
        Cursor cursorTodosUsuarios = dbAyudante.getAllUsuarios();
        ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
        while (cursorTodosUsuarios.moveToNext()){
            listaUsuarios.add( new Usuario( cursorTodosUsuarios ) );
        }
        usuarioAdaptador = new UsuarioAdapater(
                this,
                listaUsuarios
        );
         */
        usuarioAdaptador = new UsuarioCursorAdapter( this, null);
        milista.setAdapter( usuarioAdaptador );

        btnGuardar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuarioNuevo = new Usuario( nombreTxt.getText().toString(),Integer.parseInt(passwordTxt.getText().toString()) );
                dbAyudante.guardarUsuario( usuarioNuevo );
                loadPersonas();
            }
        } );

        loadPersonas();

    }

    private void loadPersonas() {
        new PersonaLoaderTask().execute( );
    }

    private class PersonaLoaderTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return dbAyudante.getAllUsuarios();
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.getCount() > 0) {
                usuarioAdaptador.swapCursor( cursor );
            }
        }
    }
}