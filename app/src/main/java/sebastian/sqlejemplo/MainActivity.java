package sebastian.sqlejemplo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    UsuarioAdapater usuarioAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        Button btnGuardar = (Button) findViewById( R.id.btnGuardar );
        EditText nombreTxt = (EditText) findViewById( R.id.usrText );
        EditText passwordTxt = (EditText) findViewById( R.id.passwordText );
        ListView milista = (ListView) findViewById( R.id.listaUsuarios );

        ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
        Usuario usuarioPrueba = new Usuario( "Pepito" ,1122);
        listaUsuarios.add(usuarioPrueba);

        usuarioAdaptador = new UsuarioAdapater(
                this,
                listaUsuarios
        );
        milista.setAdapter( usuarioAdaptador );

        dbAyudante = new AgendaDBHelper(this);

        btnGuardar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuarioNuevo = new Usuario( nombreTxt.getText().toString(),Integer.parseInt(passwordTxt.getText().toString()) );
                dbAyudante.guardarUsuario( usuarioNuevo );
            }
        } );
    }

}