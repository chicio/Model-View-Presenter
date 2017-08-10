package it.chicio.productmvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProductsFragment extends Fragment {
    private RecyclerView productsRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_products, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews();
        setupProductRecyclerView();
    }

    private void setupProductRecyclerView() {
        setupLayoutManager();
        setupStyle();
        setupAdapter();
    }

    private void setupAdapter() {
        Product[] products = {
                new Product("name", "descriptio", "image")
        };
        ProductsAdapter productsAdapter = new ProductsAdapter(products);
        productsRecyclerView.setAdapter(productsAdapter);
    }

    private void setupLayoutManager() {
        LinearLayoutManager productsLinearLayoutManager = new LinearLayoutManager(getContext());
        productsRecyclerView.setLayoutManager(productsLinearLayoutManager);
    }

    private void setupStyle() {
        productsRecyclerView.setHasFixedSize(true);
    }

    private void bindViews() {
        productsRecyclerView = (RecyclerView) getView().findViewById(R.id.products_recycler_view);
    }
}
