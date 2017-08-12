package it.chicio.productmvp.view.navigator;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import it.chicio.productmvp.R;
import it.chicio.productmvp.model.Product;
import it.chicio.productmvp.view.ProductsFragment;

public class ProductsActivity extends AppCompatActivity implements ProductsNavigator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        showFragment(ProductsFragment.newInstance(this));
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.products_activity_content, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void goToDetailOf(Product product) {
        Toast.makeText(this, "DETAIL " + product.getName(), Toast.LENGTH_LONG).show();
    }
}
