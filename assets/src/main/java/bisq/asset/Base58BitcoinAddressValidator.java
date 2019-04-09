/*
 * This file is part of Bisq.
 *
 * Bisq is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * Bisq is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Bisq. If not, see <http://www.gnu.org/licenses/>.
 */

package bisq.asset;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.AddressFormatException;
import org.bitcoinj.core.LegacyAddress;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;

/**
 * {@link AddressValidator} for Base58-encoded Bitcoin addresses.
 *
 * @author Chris Beams
 * @since 0.7.0
 * @see org.bitcoinj.core.LegacyAddress#fromBase58(NetworkParameters, String)
 */
public class Base58BitcoinAddressValidator implements AddressValidator {

    private final NetworkParameters networkParameters;

    public Base58BitcoinAddressValidator() {
        this(MainNetParams.get());
    }

    public Base58BitcoinAddressValidator(NetworkParameters networkParameters) {
        this.networkParameters = networkParameters;
    }

    @Override
    public AddressValidationResult validate(String address) {
        try {
            LegacyAddress.fromBase58(networkParameters, address);
        } catch (AddressFormatException ex) {
            return AddressValidationResult.invalidAddress(ex);
        }

        return AddressValidationResult.validAddress();
    }
}
