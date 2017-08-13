package it.chicio.productmvp.view;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import it.chicio.productmvp.R;
import it.chicio.productmvp.model.Product;
import it.chicio.productmvp.model.ProductsRepository;
import it.chicio.productmvp.presenter.ProductsPresenter;
import it.chicio.productmvp.view.navigator.ProductsNavigator;

public class ProductsFragment extends Fragment implements ProductsView {
    private RecyclerView productsRecyclerView;
    private ProductsPresenter productsPresenter;
    private LinearLayoutManager productsLinearLayoutManager;
    private ProgressBar productsProgressBar;
    private ProductsNavigator productsNavigator;

    public static ProductsFragment newInstance(ProductsNavigator productsNavigator) {
        ProductsFragment productsFragment = new ProductsFragment();
        productsFragment.productsNavigator = productsNavigator;
        return productsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_products, container, false);
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
        productsPresenter.onStart();
    }

    private void initializeDependencies() {
        ProductsRepository productsRepository = new ProductsRepository();
        productsPresenter = new ProductsPresenter(this, productsRepository);
    }

    private void setupProductRecyclerViewUsing(final Product[] products) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setupLayoutManager();
                setupStyle();
                setupAdapterUsing(products);
            }
        });
    }

    private void setupAdapterUsing(Product[] products) {
        ProductsAdapter productsAdapter = new ProductsAdapter(products, productsPresenter);
        productsRecyclerView.setAdapter(productsAdapter);
    }

    private void setupLayoutManager() {
        productsLinearLayoutManager = new LinearLayoutManager(getActivity());
        productsRecyclerView.setLayoutManager(productsLinearLayoutManager);
    }

    private void setupStyle() {
        productsRecyclerView.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                productsRecyclerView.getContext(),
                productsLinearLayoutManager.getOrientation());
        productsRecyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void bindViews() {
        productsProgressBar = (ProgressBar) getView().findViewById(R.id.products_progress_bar);
        productsRecyclerView = (RecyclerView) getView().findViewById(R.id.products_recycler_view);
    }

    @Override
    public void showLoadingStatus() {
        productsProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingStatus() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                productsProgressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void show(String title) {
        getActivity().setTitle(title);
    }

    @Override
    public void show(Product[] products) {
        setupProductRecyclerViewUsing(products);
    }

    @Override
    public void showErrorWith(final String message) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showErrorAlertWith(message);
            }
        });
    }

    private void showErrorAlertWith(String message) {
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

    @Override
    public void showDetailFor(Product product) {
        productsNavigator.goToDetailOf(product);
    }
}
