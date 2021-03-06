package org.onewarmcoat.onewarmcoat.app.adapters;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import org.onewarmcoat.onewarmcoat.app.R;
import org.onewarmcoat.onewarmcoat.app.models.PickupRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PickupsAdapter extends ParseQueryAdapter {

    ParseUser foundUser;

    public PickupsAdapter(Context context) {

        super(context, (QueryFactory<PickupRequest>) PickupRequest::getMyCompletedPickups);
    }

    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.pickup_history_item, null);
        }

        super.getItemView(object, v, parent);

        PickupRequest pickupRequest = (PickupRequest) object;

        TextView pickupDateView = (TextView) v.findViewById(R.id.pickupDate);
        Date d = pickupRequest.getCreatedAt();

        SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
        String dateStr = format.format(d);
        pickupDateView.setText(dateStr);

        TextView pickupAddressView = (TextView) v.findViewById(R.id.pickupAddress);
        String pickupAddress = pickupRequest.getAddresss();
        pickupAddressView.setText(pickupAddress);

//        TextView numItemsView = (TextView) v.findViewById(R.id.numItems);
//        double numItems = pickupRequest.getDonationValue();
//        DecimalFormat df = new DecimalFormat("#");
//        numItemsView.setText("$" + df.format(numItems));

        //TODO: this should show the categories

//        TextView itemTypeView = (TextView) v.findViewById(R.id.itemType);
//        String itemType = pickupRequest.getDonationCategories();
//        itemTypeView.setText(itemType);


        return v;
    }

}
