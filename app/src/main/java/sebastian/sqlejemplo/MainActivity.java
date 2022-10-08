package sebastian.sqlejemplo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import sebastian.sqlejemplo.data.AgendaDBHelper;
import sebastian.sqlejemplo.data.Usuario;

public class MainActivity extends AppCompatActivity implements UsuarioRecyclerAdaptador.OnItemClickListener{

    private AgendaDBHelper dbAyudante;
    //UsuarioAdapater usuarioAdaptador;
    //UsuarioCursorAdapter usuarioAdaptador;
    private UsuarioRecyclerAdaptador usuarioAdaptador;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        //setContentView( R.layout.activity_main );
        setContentView( R.layout.activity_main_v2 );
        Button btnGuardar = (Button) findViewById( R.id.btnGuardar );
        EditText nombreTxt = (EditText) findViewById( R.id.usrText );
        EditText passwordTxt = (EditText) findViewById( R.id.passwordText );
        //ListView milista = (ListView) findViewById( R.id.listaUsuarios );
        RecyclerView milista = (RecyclerView) findViewById( R.id.recyclerListusuarios );
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
        //usuarioAdaptador = new UsuarioCursorAdapter( this, null);
        milista.setHasFixedSize( true );
        linearLayoutManager = new LinearLayoutManager( this );
        milista.setLayoutManager( linearLayoutManager );
        usuarioAdaptador = new UsuarioRecyclerAdaptador( this );
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

    @Override
    public void onClick(UsuarioRecyclerAdaptador.ViewHolder view) {

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