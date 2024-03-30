package com.agaafar.tictactoe

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.agaafar.tictactoe.Adapter.MarketAdapter
import com.agaafar.tictactoe.Adapter.MarketAdapter.onItemClickListener
import com.agaafar.tictactoe.Adapter.marketplacedata
import com.agaafar.tictactoe.data.userViewModel
import com.agaafar.tictactoe.data.userViewModelFactory
import com.agaafar.tictactoe.databinding.FragmentMarketPlaceBinding
import com.google.android.gms.ads.*
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.newFixedThreadPoolContext

class MarketPlaceFragment : Fragment()  {
    private val userViewModel: userViewModel by viewModels {
        userViewModelFactory((activity?.application as TicTacToeApplication).repository)
    }

    private var mRewardedAd: RewardedAd? = null
    private var TAG = "MarketPlaceFrag"
    private val args by navArgs<MarketPlaceFragmentArgs>()
    private lateinit var binding:FragmentMarketPlaceBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMarketPlaceBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            binding.money.text = user[0].score.toString()
        })
        // getting the recyclerview by its id
        val recyclerview = binding.recyclerView

        // this creates a vertical layout Manager
        recyclerview.layoutManager = GridLayoutManager(this.requireContext(),2)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<marketplacedata>()


        // the image with the count of view



           val firstvidsToBeWatched = 1
           val firstvidsWatched = 0
           val secondvidsToBeWatched = 2
           val secondvidsWatched = 0
           val thirdvidsToBeWatched = 3
           val thirdvidsWatched = 0
           val forthvidsToBeWatched = 4
           val forthvidsWatched = 0
          /* val fifthvidsToBeWatched = 10
           val fifthvidsWatched = 0*/
           var money = args.score
           val currentSkin = args.currentskin
           val price = 200


        //list of ints for skins
            data.add(marketplacedata(R.drawable._skinpreview, "Select",R.drawable.ic_baseline_games_24,1,0,false,true))
            data.add(marketplacedata(R.drawable.skin2, "$firstvidsWatched / $firstvidsToBeWatched",R.drawable.advertising__4_,2,1,true,false))
            data.add(marketplacedata(R.drawable.skin3, "$secondvidsWatched / $secondvidsToBeWatched",R.drawable.advertising__4_,3,2,true,false))
            data.add(marketplacedata(R.drawable.skin4, "$thirdvidsWatched / $thirdvidsToBeWatched",R.drawable.advertising__4_,4,3,true,false))
            data.add(marketplacedata(R.drawable.skin5,"$price",R.drawable.ic_baseline_money_24,5,200,false,false))
            data.add(marketplacedata(R.drawable.skin6,"$forthvidsWatched / $forthvidsToBeWatched",R.drawable.ic_baseline_money_24,6,4,true,false))


        // This will pass the ArrayList to our Adapter
        val adapter = MarketAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        MobileAds.initialize(requireContext()) {}
        var adRequest = AdRequest.Builder().build()


        val skins = args.skins.asList()
        skins.toMutableList().distinct()




        data.forEach{
                marketplace ->
           skins.forEach {
                if(marketplace.skinId == it){
                    marketplace.price = 0
                    marketplace.ads = false
                    adapter.notifyDataSetChanged()
                }
            }
            adapter.notifyDataSetChanged()

        }



        /*userViewModel.readAllData.observe(viewLifecycleOwner, Observer {
                user ->
            data.forEach{
                    marketplace ->
                user[0].skins.forEach {
                    if(marketplace.skinId == it){
                        Log.d("Skins", "skins: $it")
                        marketplace.price = 0
                        adapter.notifyDataSetChanged()
                    }
                }
                adapter.notifyDataSetChanged()

            }


        })*/


        data.forEach {
            if(it.skinId == currentSkin){
                it.background = R.drawable.picked_item
                it.text = "Picked"
            }

            if (it.price == 0 && it.skinId != currentSkin ){
                it.text = "Select"
                it.ads = false
            }
            //search in database for skins purchased and change its price to 0
            adapter.notifyDataSetChanged()
        }

        val listener = object : onItemClickListener {
            override fun onItemClick(position: Int) {
                if (data.get(position).ads == false) {
                    if (money >= data.get(position).price){
                        money -= data.get(position).price
                        val newSkin = data.get(position).skinId
                        val newSkinList = skins.toMutableList()
                        newSkinList.add(newSkin)
                        userViewModel.updateMoney(money,1)
                        userViewModel.updateSkin(data.get(position).skinId, 1)
                        userViewModel.updateOwnendSkin(newSkinList.distinct().toList(),1)
                        data.get(position).text = "Picked"
                        data.get(position).background = R.drawable.picked_item
                        data.get(position).price = 0
                        //remaining list items
                        // to get the result as list
                        data.forEach{
                            if (it.skinId != data.get(position).skinId ){
                                if (it.ads == true){
                                    it.background = R.drawable.thirdplayerbtnback
                                    it.text = "${it.watchedAds} / ${it.price}"
                                }else{
                                    it.background = R.drawable.thirdplayerbtnback
                                    it.text = it.price.toString()
                                }
                            }
                            if (it.price == 0 && it != data.get(position) ){
                                it.text = "Select"
                            }
                            adapter.notifyDataSetChanged()
                        }

                      //  Log.d("TagMon", "$money your money: price $price  remaining items $ItemsRemained")
                    }else{
                        Snackbar.make(requireView(),"No Enough Money",Snackbar.LENGTH_LONG).show()
                    }
                }
                else if (data.get(position).ads == true){
                    val adsShouldBeWatched = data.get(position).price
                    val watchedAds:Int = data.get(position).watchedAds

                    if (watchedAds == adsShouldBeWatched){
                        val newSkin = data.get(position).skinId
                        val newSkinList = skins.toMutableList()
                        newSkinList.add(newSkin)
                        userViewModel.updateSkin(newSkin, 1)
                        userViewModel.updateOwnendSkin(newSkinList.distinct().toList(),1)
                        skins.toMutableList().add(newSkin )
                        data.get(position).text = "Picked"
                        data.get(position).background = R.drawable.picked_item
                        data.get(position).price = 0
                        data.get(position).ads = false
                        adapter.notifyDataSetChanged()




                        data.forEach{
                            if (it.skinId != data.get(position).skinId){
                                if (it.ads == true){
                                    it.background = R.drawable.thirdplayerbtnback
                                    it.text = "${it.watchedAds} / ${it.price}"
                                }else{
                                    it.background = R.drawable.thirdplayerbtnback
                                    it.text = it.price.toString()
                                }

                            }
                            if (it.price == 0 && it != data.get(position) ){
                                it.text = "Select"
                                it.background = R.drawable.thirdplayerbtnback
                            }
                            adapter.notifyDataSetChanged()
                        }
                        adapter.notifyDataSetChanged()


                    }else{
                        RewardedAd.load(requireContext(),"ca-app-pub-3940256099942544/5224354917", adRequest, object : RewardedAdLoadCallback() {
                            override fun onAdFailedToLoad(adError: LoadAdError) {
                                //error message
                                mRewardedAd = null
                            }

                            override fun onAdLoaded(rewardedAd: RewardedAd) {
                                //ad was loaded

                                mRewardedAd = rewardedAd


                            }
                        })//this requests the add

                    }



                    mRewardedAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
                        override fun onAdShowedFullScreenContent() {
                            // Called when ad is shown.

                            data.get(position).watchedAds += 1
                            data.get(position).text = "${data.get(position).watchedAds} / ${data.get(position).price}"

                            adapter.notifyDataSetChanged()

                        }

                        override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                            // Called when ad fails to show.

                            //show dialog
                        }

                        override fun onAdDismissedFullScreenContent() {
                            // Called when ad is dismissed.
                            // Set the ad reference to null so you don't show the ad a second time.
                            mRewardedAd = null
                            //show dialog
                        }
                    }

                    if (mRewardedAd != null) {
                        mRewardedAd?.show(requireActivity(), OnUserEarnedRewardListener() {
                            fun onUserEarnedReward(rewardItem: RewardItem) {
                                var rewardAmount = rewardItem.amount
                                var rewardType = rewardItem.getType()

                            }
                        })
                    } else {

                    }




                }
                adapter.notifyDataSetChanged()
            }
        }

        adapter.setOnItemClickListener(listener)

        binding.backbtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }


}