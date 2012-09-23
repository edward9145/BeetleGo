package game.edw.beetlego;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class DebugFrame extends Activity {
	
	EditText etStageLen;
	EditText etRocketAX;
	EditText etRocketAN;
	EditText etRocketBX;
	EditText etRocketBN;
	EditText etCloudX;
	EditText etCactusX;
	
	EditText etHeartX;
	EditText etHeartY;
	EditText etHeartAdd;
	EditText etPlaneY;
	EditText etPlaneLife;
	EditText etPlaneMaxLife;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
        setContentView(R.layout.debug);
        
        etStageLen = (EditText)findViewById(R.id.etStageLen);
        
        etRocketAX = (EditText)findViewById(R.id.etRocketAX);
        etRocketAN = (EditText)findViewById(R.id.etRocketAN);
        etRocketBX = (EditText)findViewById(R.id.etRocketBX);
        etRocketBN = (EditText)findViewById(R.id.etRocketBN);
        etCloudX = (EditText)findViewById(R.id.etCloudX);
        etCactusX = (EditText)findViewById(R.id.etCactusX);
        
        etHeartX = (EditText)findViewById(R.id.etHeartX);
        etHeartY = (EditText)findViewById(R.id.etHeartY);
        etHeartAdd = (EditText)findViewById(R.id.etHeartAdd);
        etPlaneY = (EditText)findViewById(R.id.etPlaneY);
        etPlaneLife = (EditText)findViewById(R.id.etPlaneLife);
        etPlaneMaxLife = (EditText)findViewById(R.id.etPlaneMaxLife);
        
        readSetting();       
    }
	
	
	public void readSetting(){
		etStageLen.setText(C.STAGE_LENGTH +"");
		
		etRocketAX.setText(C.ROCKETA_SPEEDX +"");
        etRocketAN.setText(C.ROCKETA_NUM +"");
        etRocketBX.setText(C.ROCKETB_SPEEDX +"");
        etRocketBN.setText(C.ROCKETB_NUM +"");
        etCloudX.setText(C.CLOUD_SPEEDX +"");
        etCactusX.setText(C.CACTUS_SPEEDX +"");
        
        etHeartX.setText(C.HEART_SPEEDX +"");
        etHeartY.setText(C.HEART_SPEEDY +"");
        etHeartAdd.setText(C.HEART_ADD_LIFE +"");
        etPlaneY.setText(C.PLANE_SPEEDY_MAX +"");
        etPlaneLife.setText(C.PLANE_LIFE +"");
        etPlaneMaxLife.setText(C.PLANE_MAXLIFE +""); 
	}
	
	public void setOK(View view){
		
		try{
		C.STAGE_LENGTH = Integer.parseInt(etStageLen.getText().toString());
		
		C.ROCKETA_SPEEDX = Float.parseFloat(etRocketAX.getText().toString());
		C.ROCKETA_NUM = Integer.parseInt(etRocketAN.getText().toString());
		C.ROCKETB_SPEEDX = Float.parseFloat(etRocketBX.getText().toString());
		C.ROCKETB_NUM = Integer.parseInt(etRocketBN.getText().toString());
		C.CLOUD_SPEEDX = Float.parseFloat(etCloudX.getText().toString());
		C.CACTUS_SPEEDX = Float.parseFloat(etCactusX.getText().toString());
		
		C.HEART_SPEEDX = Float.parseFloat(etHeartX.getText().toString());
		C.HEART_SPEEDY = Float.parseFloat(etHeartY.getText().toString());
		C.HEART_ADD_LIFE = Integer.parseInt(etHeartAdd.getText().toString());
		C.PLANE_SPEEDY_MAX = Float.parseFloat(etPlaneY.getText().toString());
		C.PLANE_LIFE = Integer.parseInt(etPlaneLife.getText().toString());
		C.PLANE_MAXLIFE = Integer.parseInt(etPlaneMaxLife.getText().toString());
		}
		catch(Exception e){
			showMsg(e.toString());
		}
		
		finish();
	}
	
	public void showMsg(final String s){
		this.runOnUiThread(new Runnable() {
			public void run(){
				Toast.makeText(DebugFrame.this, s, Toast.LENGTH_LONG)
				.show();
			}
		});
	}
	
}
