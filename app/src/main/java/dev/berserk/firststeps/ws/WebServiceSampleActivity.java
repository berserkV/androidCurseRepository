package dev.berserk.firststeps.ws;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.berserk.firststeps.R;
import dev.berserk.firststeps.ws.wsmodels.SWCharacter;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebServiceSampleActivity extends AppCompatActivity {

    @BindView(R.id.editTextPokemon)
    EditText mEditTextPokemon;

    @BindView(R.id.textViewPokemon)
    TextView mTextViewPokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service_sample);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonPokemon) public void clickPokemon(View view) {
        String input = mEditTextPokemon.getText().toString();
        if (!input.isEmpty()) {
            Client client = ServiceGenerator.createService(Client.class);
            client.getCharacter(input)
                    .enqueue(new Callback<SWCharacter>() {
                        @Override
                        public void onResponse(Call<SWCharacter> call, Response<SWCharacter> response) {
                            if(response.code() == 200) {
                                SWCharacter character = response.body();
                                mTextViewPokemon.setText(character.getName());
                            }
                            else {
                                mTextViewPokemon.setText("Code error: "+response.code());
                            }
                        }

                        @Override
                        public void onFailure(Call<SWCharacter> call, Throwable t) {
                            mTextViewPokemon.setText("ERROR!!!!!!!!!");
                            t.printStackTrace();
                        }
                    });
                    /*.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            try {
                                mTextViewPokemon.setText(new JSONObject(response.body().string()).getString("name"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            mTextViewPokemon.setText("ERROR!!!!!!!!!");
                            t.printStackTrace();
                        }
                    });*/
                    /*.enqueue(new Callback<Response<ResponseBody>>() {
                        @Override
                        public void onResponse(Call<Response<ResponseBody>> call, Response<Response<ResponseBody>> response) {
                            mTextViewPokemon.setText(response.body().toString());
                        }

                        @Override
                        public void onFailure(Call<Response<ResponseBody>> call, Throwable t) {
                            mTextViewPokemon.setText("ERROR!!!!!!!!!");
                            t.printStackTrace();
                        }
                    });*/
        }
    }
}
