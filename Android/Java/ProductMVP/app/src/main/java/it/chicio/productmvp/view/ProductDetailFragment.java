package it.chicio.productmvp.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import it.chicio.productmvp.R;
import it.chicio.productmvp.model.Product;
import it.chicio.productmvp.presenter.ProductDetailPresenter;

public class ProductDetailFragment extends Fragment implements ProductDetailView {
    private Product product;
    private ImageView productImageView;
    private TextView productNameTextView;
    private TextView productDescriptionTextView;
    private ProductDetailPresenter productDetailPresenter;

    public static ProductDetailFragment newInstance(Product product) {
        ProductDetailFragment productDetailFragment = new ProductDetailFragment();
        productDetailFragment.product = product;
        return productDetailFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews();
        initializeDependencies();
    }

    @Override
    public void onResume() {
        super.onResume();
        productDetailPresenter.onStart();
    }

    private void bindViews() {
        productImageView = (ImageView) getActivity().findViewById(R.id.product_image_detail_image_view);
        productNameTextView = (TextView) getActivity().findViewById(R.id.product_name_detail_text_view);
        productDescriptionTextView = (TextView) getActivity().findViewById(R.id.product_description_detail_text_view);
    }

    private void initializeDependencies() {
        productDetailPresenter = new ProductDetailPresenter(this, product);
    }

    @Override
    public void show(String title) {
        getActivity().setTitle(title);
    }

    @Override
    public void show(Product product) {
        productImageView.setImageDrawable(getActivity().getDrawable(getActivity().getResources().getIdentifier(
                product.getImage(),
                "drawable",
                getActivity().getPackageName()
        )));
        productNameTextView.setText(product.getName());
        productDescriptionTextView.setText(product.getDescription());
    }

    @Override
    public void showErrorWith(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setTitle(R.string.dialog_error_title)
                .setPositiveButton(R.string.dialog_error_ok_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog errorAlert = builder.create();
        errorAlert.show();
    }
}
