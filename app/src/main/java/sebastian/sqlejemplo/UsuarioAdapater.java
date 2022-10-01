package sebastian.sqlejemplo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import sebastian.sqlejemplo.data.Usuario;

public class UsuarioAdapater extends ArrayAdapter<Usuario> {
    public UsuarioAdapater(Context context, List<Usuario> objects){
        super(context,0,objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );

        //Existe el view actual?
        if (null == convertView){
            convertView = inflater.inflate(
                    R.layout.list_item_usuarios,
                    parent,
                    false
            );
        }

        TextView nameList = (TextView) convertView.findViewById( R.id.listNamePassword );
        TextView passwordList = (TextView) convertView.findViewById( R.id.listPasswordUser );

        Usuario usuarioActual = getItem( position );

        nameList.setText(usuarioActual.getName());
        passwordList.setText( usuarioActual.getPassword().toString() );

        return convertView;
    }
}
