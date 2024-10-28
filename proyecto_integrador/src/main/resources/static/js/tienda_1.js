
document.getElementById('toggleView').addEventListener('click', function() {
    var productContainer = document.getElementById('productContainer');
    
    if (productContainer.classList.contains('grid-view')) 
    {
        productContainer.classList.remove('grid-view');
        productContainer.classList.add('list-view');
    } 
    else 
    {
        productContainer.classList.remove('list-view');
        productContainer.classList.add('grid-view');
    }
});
